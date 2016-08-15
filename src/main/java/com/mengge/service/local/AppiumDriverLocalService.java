/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mengge.service.local;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import org.apache.commons.lang3.StringUtils;

import org.openqa.selenium.net.UrlChecker;
import org.openqa.selenium.os.CommandLine;
import org.openqa.selenium.remote.service.DriverService;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public final class AppiumDriverLocalService extends DriverService {

    private static final String URL_MASK = "http://%s:%d/wd/hub";
    private final File nodeJSExec;
    private final int nodeJSPort;
    private final ImmutableList<String> nodeJSArgs;
    private final ImmutableMap<String, String> nodeJSEnvironment;
    private final String ipAddress;
    private final long startupTimeout;
    private final TimeUnit timeUnit;
    private final ReentrantLock lock = new ReentrantLock(true); //uses "fair" thread ordering policy
    private final ListOutputStream stream = new ListOutputStream().add(System.out);



    private CommandLine process = null;

    AppiumDriverLocalService(String ipAddress, File nodeJSExec, int nodeJSPort,
        ImmutableList<String> nodeJSArgs, ImmutableMap<String, String> nodeJSEnvironment,
        long startupTimeout, TimeUnit timeUnit) throws IOException {
        super(nodeJSExec, nodeJSPort, nodeJSArgs, nodeJSEnvironment);
        this.ipAddress = ipAddress;
        this.nodeJSExec = nodeJSExec;
        this.nodeJSPort = nodeJSPort;
        this.nodeJSArgs = nodeJSArgs;
        this.nodeJSEnvironment = nodeJSEnvironment;
        this.startupTimeout = startupTimeout;
        this.timeUnit = timeUnit;
    }

    public static AppiumDriverLocalService buildDefaultService() {
        return buildService(new AppiumServiceBuilder());
    }

    public static AppiumDriverLocalService buildService(AppiumServiceBuilder builder) {
        return builder.build();
    }

    /**
     * @return 返回appium服务端地址.
     */
    @Override public URL getUrl() {
        try {
            return new URL(String.format(URL_MASK, ipAddress, nodeJSPort));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override public boolean isRunning() {
        lock.lock();
        try {
            if (process == null) {
                return false;
            }

            if (!process.isRunning()) {
                return false;
            }

            try {
                ping(1500, TimeUnit.MILLISECONDS);
                return true;
            } catch (UrlChecker.TimeoutException e) {
                return false;
            }
        } finally {
            lock.unlock();
        }

    }

    private void ping(long time, TimeUnit timeUnit) throws UrlChecker.TimeoutException {
        URL url = getUrl();
        try {
            URL status = new URL(url.toString() + "/status");
            new UrlChecker().waitUntilAvailable(time, timeUnit, status);
        } catch (MalformedURLException e) {
            throw new RuntimeException(
                "URL出错 " + url.toString().toString() + "/status");
        }
    }

    /**
     * 启动appium服务端
     *
     *
     * @throws AppiumServerHasNotBeenStartedLocallyException
     * 如果在产生子进程中发生错误,则抛此异常
     *
     * @see #stop()
     */
    public void start() throws AppiumServerHasNotBeenStartedLocallyException {
        lock.lock();
        try {
            if (isRunning()) {
                return;
            }

            try {
                process = new CommandLine(this.nodeJSExec.getCanonicalPath(),
                    nodeJSArgs.toArray(new String[] {}));
                process.setEnvironmentVariables(nodeJSEnvironment);
                process.copyOutputTo(stream);
                process.executeAsync();
                ping(startupTimeout, timeUnit);
            } catch (Throwable e) {
                destroyProcess();
                String msgTxt = "本地appium服务尚未启动. "
                    + "Node.js路径: " + this.nodeJSExec.getAbsolutePath()
                    + " 参数: " + nodeJSArgs.toString() + " " + "\n";
                if (process != null) {
                    String processStream = process.getStdOut();
                    if (!StringUtils.isBlank(processStream)) {
                        msgTxt = msgTxt + "Process output: " + processStream + "\n";
                    }
                }

                throw new AppiumServerHasNotBeenStartedLocallyException(msgTxt, e);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 停止正在运行的服务. 此方法会一直去尝试停止服务,直到服务端被完全关掉
     *
     * @see #start()
     */
    @Override public void stop() {
        lock.lock();
        try {
            if (process != null) {
                destroyProcess();
            }
            process = null;
        } finally {
            lock.unlock();
        }
    }

    private void destroyProcess() {
        if (process.isRunning()) {
            process.destroy();
        }
    }

    /**
     * @return 如果服务端启动成功,开始记录log. 否则返回null.
     */
    public String getStdOut() {
        if (process != null) {
            return process.getStdOut();
        }

        return null;
    }

    /**
     * 增加其他输出流用于接受服务端输出数据
     * @param outputStream {@link java.io.OutputStream}的实例,它被用于准备接受服务端的输出数据.
     */
    public void addOutPutStream(OutputStream outputStream) {
        checkNotNull(outputStream, "outputStream参数为NULL!");
        stream.add(outputStream);
    }

    /**
     * 增加其他输出流用于接受服务端输出数据
     * @param outputStreams {@link java.io.OutputStream}的集合,它被用于准备接受服务端的输出数据
     */
    public void addOutPutStreams(List<OutputStream> outputStreams) {
        checkNotNull(outputStreams, "outputStream参数为NULL!");
        for (OutputStream stream : outputStreams) {
            addOutPutStream(stream);
        }
    }

}
