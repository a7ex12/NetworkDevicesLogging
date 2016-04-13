/*
 *    Copyright 2016 Alexander Samtsov
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.alexandersamtsov.networkdeviceslogging;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BashCommand extends Thread {

    public BashCommand(String cmd) {
        super(cmd);
    }

    public void run() {

        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            process = runtime.exec(getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (process != null) {
                process.waitFor();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BufferedReader br = null;
        if (process != null) {
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        }
        try {
            if (br != null) {
                while (br.ready())

                {
                    System.out.println(br.readLine());
                }
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
