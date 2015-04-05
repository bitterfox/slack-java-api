/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Files;
import java.util.Objects;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class FilesListTest extends AbstractApiTest<Files, Files.List>
{
    public FilesListTest()
    {
        super(Slack::files, Files::list);
    }

    @Result("{\"ok\":true,"
        + "\"files\":[{}, {}, {}],"
        + "\"paging\":{"
        + "\"count\":100,"
        + "\"total\":200,"
        + "\"page\":1,"
        + "\"pages\":2}}")
    @Test
    public void test()
    {
        Files.List list = this.call();

        Assert.assertEquals(3, list.files().size());
        Objects.requireNonNull(list.paging());
    }
}
