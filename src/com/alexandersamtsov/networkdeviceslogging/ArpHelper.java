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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ArpHelper {


    public ArrayList<String> getUpArp()
    {

        ArrayList<String> list = new ArrayList<>();
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader("/proc/net/arp"));

            try {
                while ((line = br.readLine()) != null) {
                    String[] splitted = line.split(" +");
                    if (splitted.length >= 4 && ((splitted[2].equals("0x2")) || (splitted[2].equals("0x0")))) {
                        if (!splitted[3].matches("00:00:00:00:00:00")) {
                            list.add(line);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return list;


    }

}
