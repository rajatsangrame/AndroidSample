package com.example.daggerdemo.sample.util;


import com.example.daggerdemo.sample.model.RandomUsers;
import com.example.daggerdemo.sample.model.Result;

import java.util.List;

/**
 * Created by Rajat Sangrame on 29/5/20.
 * http://github.com/rajatsangrame
 */
public class Utils {


    public static List<Result> getListResult(RandomUsers randomUsers) {
        return randomUsers.getResults();
    }
}
