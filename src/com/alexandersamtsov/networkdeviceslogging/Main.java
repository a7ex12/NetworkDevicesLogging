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

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import static com.alexandersamtsov.networkdeviceslogging.Constants.*;

public class Main {

    public static void main(String[] args) {


        String packagePath = "/com/alexandersamtsov/networkdeviceslogging/";

        FileWriter outputFile = null;
        try {
            outputFile = new FileWriter(System.getProperty("user.dir") + "/src" + packagePath + "netdevlog", true);
            //outputFile = new FileWriter("netdevlog", true); // use it when make jar project
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bwr = null;
        if (outputFile != null) {
            bwr = new BufferedWriter(outputFile);
        }


        BashCommand nmapDisc = new BashCommand("nmap -sn 192.168.100.0/24");
        nmapDisc.start();
        try {
            nmapDisc.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(new Date().toString());
        try {
            if (bwr != null) {
                bwr.write(new Date().toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String str : new ArpHelper().getUpArp())
        {
            if(str.contains(router.toLowerCase()) || str.contains(router.toUpperCase())) {
                System.out.println(str + " - router");
                try {
                    if (bwr != null) {
                        bwr.write(str + " - router" + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(str.contains(PC1.toLowerCase()) || str.contains(PC1.toUpperCase()))
            {
                System.out.println(str + " - it's PC1");
                try {
                    if (bwr != null) {
                        bwr.write(str + " - it's PC1" + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(str.contains(Phone1.toLowerCase()) || str.contains(Phone1.toUpperCase()))
            {
                System.out.println(str + " - it's Phone1");
                try {
                    if (bwr != null) {
                        bwr.write(str + " - it's Phone1" + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(str.contains(Phone2.toLowerCase()) || str.contains(Phone2.toUpperCase()))
            {
                System.out.println(str + " - it's Phone2");
                try {
                    if (bwr != null) {
                        bwr.write(str + " - it's Phone2" + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                System.out.println(str + " - unknown device");
                try {
                    if (bwr != null) {
                        bwr.write(str + " - unknown device" + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        try {
            if (bwr != null) {
                bwr.newLine();
            }
            if (bwr != null) {
                bwr.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }





    }
}
