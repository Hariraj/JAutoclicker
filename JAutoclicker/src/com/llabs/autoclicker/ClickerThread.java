/*
 * Autoclicker ----------------- Copyright 20012-2013 JAutoclicker
 * 
 * I always give it my best shot to make a program useful and solid, but remember
 * that there is absolutely no warranty for using this program as stated in the
 * following terms:
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.llabs.autoclicker;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hariraj
 */
public class ClickerThread extends Thread {

    private FormBean formBean;
    private int runningstate;

    public FormBean getFormBean() {
        return formBean;
    }

    public void setFormBean(FormBean formBean) {
        this.formBean = formBean;
    }

    public int getRunningstate() {
        return runningstate;
    }

    public void setRunningstate(int runningstate) {
        this.runningstate = runningstate;
    }
    public ClickerThread(FormBean formBean)
    {
        this.formBean=formBean;
    }
    @Override
    public void run() {
        try {
            long i=1;
            int j,k;
            final Robot robot = new Robot();
            int button1,button2,button3;
            if(formBean.getC1()==0||formBean.getC1()==3){
                button1=InputEvent.BUTTON1_MASK;
            }
            else if(formBean.getC1()==1){
                 button1=InputEvent.BUTTON2_MASK;
            }
            else{
                 button1=InputEvent.BUTTON3_MASK;
            }

            
            if(formBean.getC2()==0||formBean.getC2()==3){
                button2=InputEvent.BUTTON1_MASK;
            }
            else if(formBean.getC2()==1){
                 button2=InputEvent.BUTTON2_MASK;
            }
            else{
                 button2=InputEvent.BUTTON3_MASK;
            }

            if(formBean.getC3()==0||formBean.getC3()==3){
                button3=InputEvent.BUTTON1_MASK;
            }
            else if(formBean.getC3()==1){
                 button3=InputEvent.BUTTON2_MASK;
            }
            else{
                 button3=InputEvent.BUTTON3_MASK;
            }
           do{
                robot.mouseMove(formBean.getX1(), formBean.getY1());
                for(j=0;j<formBean.getL1();j++)
                {
                    if(formBean.getC1()==3||formBean.getC1()==4){
                        robot.mousePress(button1);
                        robot.mouseRelease(button1);
                        robot.mousePress(button1);
                        robot.mouseRelease(button1);
                    }
                    else
                    {
                        robot.mousePress(button1);
                        robot.mouseRelease(button1);
                    }
                    robot.delay(formBean.getD1());
                }
                
                robot.mouseMove(formBean.getX2(), formBean.getY2());
                for(j=0;j<formBean.getL2();j++)
                {
                    if(formBean.getC2()==3||formBean.getC2()==4){
                        robot.mousePress(button2);
                        robot.mouseRelease(button2);
                        robot.mousePress(button2);
                        robot.mouseRelease(button2);
                    }
                    else
                    {
                        robot.mousePress(button2);
                        robot.mouseRelease(button2);
                    }
                    robot.delay(formBean.getD2());
                }
                
                robot.mouseMove(formBean.getX3(), formBean.getY3());
                for(j=0;j<formBean.getL3();j++)
                {
                    if(formBean.getC3()==3||formBean.getC3()==4){
                        robot.mousePress(button3);
                        robot.mouseRelease(button3);
                        robot.mousePress(button3);
                        robot.mouseRelease(button3);
                    }
                    else
                    {
                        robot.mousePress(button3);
                        robot.mouseRelease(button3);
                    }
                    robot.delay(formBean.getD3());
                }
            }while(i++ != formBean.getCount());
            System.exit(0);
        } catch (Exception ex) {
             Logger.getLogger(ClickerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
