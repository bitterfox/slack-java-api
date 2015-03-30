/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.data.SharedFile;
import com.slack.data.SharedFileComment;
import com.slack.data.SharedFileId;

/**
 *
 * @author bitter_fox
 */
public interface Files
{
    @ApiIssuer
    Files.Delete delete(SharedFileId fileId);

    @ApiIssuer
    Files.Info info(SharedFileId fileId);

    @ApiIssuer
    Files.List list();

    interface Delete {}

    interface Info
    {
        SharedFile file();
        java.util.List<SharedFileComment> comments();
        Paging paging();
    }

    interface List
    {
        java.util.List<SharedFile> files();
        Paging paging();
    }
}
