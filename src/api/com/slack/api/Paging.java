/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

/**
 *
 * @author bitter_fox
 */
public interface Paging
{
    int count();
    int total();
    int page();
    int pages();
}
