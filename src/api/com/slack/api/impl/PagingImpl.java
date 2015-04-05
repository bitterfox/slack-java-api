/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.api.Paging;
import static com.slack.api.impl.Names.COUNT;
import static com.slack.api.impl.Names.PAGE;
import static com.slack.api.impl.Names.PAGES;
import static com.slack.api.impl.Names.TOTAL;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
public class PagingImpl implements Paging
{
    private int count;
    private int total;
    private int page;
    private int pages;

    public void count(int count)
    {
        this.count = count;
    }

    @Override
    public int count()
    {
        return count;
    }

    public void total(int total)
    {
        this.total = total;
    }

    @Override
    public int total()
    {
        return total;
    }

    public void page(int page)
    {
        this.page = page;
    }

    @Override
    public int page()
    {
        return page;
    }

    public void pages(int pages)
    {
        this.pages = pages;
    }

    @Override
    public int pages()
    {
        return pages;
    }

    @Override
    public String toString()
    {
        return "PagingImpl{" + "count=" + count + ", total=" + total + ", page=" + page + ", pages=" + pages + '}';
    }

    public static Paging unmarshal(JsonObject jo) // TODO
    {
        PagingImpl paging = new PagingImpl();

        paging.count = jo.getInt(COUNT);
        paging.total = jo.getInt(TOTAL);
        paging.page = jo.getInt(PAGE);
        paging.pages = jo.getInt(PAGES);

        return paging;
    }
}
