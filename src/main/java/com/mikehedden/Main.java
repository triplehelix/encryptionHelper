package com.mikehedden;

import com.mikehedden.util.Cli;

public class Main {

    public static void main(String[] args) {
	// write your code here
        if (null != args){
            new Cli(args).parse();
        }
    }
}
