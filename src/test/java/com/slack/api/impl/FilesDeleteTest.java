/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Files;
import com.slack.data.impl.SharedFileIdImpl;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class FilesDeleteTest extends AbstractApiTest<Files, Files.Delete>
{
    public FilesDeleteTest()
    {
        super(Slack::files, files -> files.delete(new SharedFileIdImpl("")));
    }
    
    @Result("{\"ok\":true}")
    @Test
    public void test()
    {
        this.call();
    }
}
