/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Files;
import com.slack.data.impl.SharedFileIdImpl;
import java.util.Objects;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class FilesInfoTest extends AbstractApiTest<Files, Files.Info>
{
    public FilesInfoTest()
    {
        super(Slack::files, files -> files.info(new SharedFileIdImpl("")));
    }

    @Result("{\"ok\":true,"
        + "\"file\":{}"
        + "\"comments\":[{}, {}, {}],"
        + "\"paging\":{"
        + "\"count\":100,"
        + "\"total\":200,"
        + "\"page\":1,"
        + "\"pages\":2}}")
    @Test
    public void test()
    {
        Files.Info info = this.call();

        Objects.requireNonNull(info.file());
        Assert.assertEquals(3, info.comments().size());
        Objects.requireNonNull(info.paging());
    }
}
