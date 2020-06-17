package com.leo.auction.utils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Leo on 2018/10/18.
 */

public class HashMapUtils {

    //获取当前选项
    public static ArrayList<Integer> getOption(HashMap<Integer, Boolean> hashMap) {
        ArrayList<Integer> checkList = new ArrayList<>();
        for (int i = 0; i < hashMap.size(); i++) {
            if (hashMap.get(i)) {
                checkList.add(i);
            }
        }
        return checkList;
    }


    //重置
    public static void resetOption(HashMap<Integer, Boolean> hashMap) {
        for (int i = 0; i < hashMap.size(); i++) {
            hashMap.put(i, false);
        }
        hashMap.put(0, true);
    }


    //多选
    public static void multipleOption(HashMap<Integer, Boolean> hashMap, int position) {
        int checkNum = 0;
        if (hashMap.get(position)) {
            hashMap.put(position, false);
        } else {
            hashMap.put(position, true);
        }

        for (int i = 1; i < hashMap.size(); i++) {
            if (hashMap.get(i)) {
                checkNum++;
            }
        }

        if (checkNum == 0) {
            hashMap.put(0, true);
        } else {
            hashMap.put(0, false);
        }
    }


    //单选 ---带有全部选中功能
//    void singleOption(HashMap<Integer,Boolean> hashMap, ArrayList<? extends BaseJson> jsonArrayList,int position ){
    public static void singleOption(HashMap<Integer, Boolean> hashMap, int position) {
        if (position > 0 && hashMap.get(position)) {
            for (int i = 0; i < hashMap.size(); i++) {
                hashMap.put(i, false);
            }
            hashMap.put(0, true);
        } else if (position > -1) {
            for (int i = 0; i < hashMap.size(); i++) {
                hashMap.put(i, false);
            }
            hashMap.put(position, true);
        } else {
            for (int i = 0; i < hashMap.size(); i++) {
                hashMap.put(i, false);
            }
        }
    }


    //单选 ---带有全部选中功能
//    void singleOption(HashMap<Integer,Boolean> hashMap, ArrayList<? extends BaseJson> jsonArrayList,int position ){
    public static void singleChoice(HashMap<Integer, Boolean> hashMap, int position) {
        if (hashMap.get(position)) {
            for (int i = 0; i < hashMap.size(); i++) {
                hashMap.put(i, false);
            }
            hashMap.put(position, true);
        } else {
            hashMap.put(position, false);
        }
    }


}
