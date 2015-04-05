/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ApiIssuer;
import com.slack.api.Files;
import com.slack.api.Paging;
import static com.slack.api.impl.Names.COMMENTS;
import static com.slack.api.impl.Names.DELETE;
import static com.slack.api.impl.Names.FILE;
import static com.slack.api.impl.Names.FILES;
import static com.slack.api.impl.Names.INFO;
import static com.slack.api.impl.Names.LIST;
import static com.slack.api.impl.Names.PAGING;
import com.slack.data.SharedFile;
import com.slack.data.SharedFileComment;
import com.slack.data.SharedFileId;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
class FilesImpl implements Files
{
    private Api api;
    private Slack slack;

    public FilesImpl(Slack slack)
    {
        this.slack = slack;
        api = new Api(slack, FILES);
    }
    
    @ApiIssuer
    @Override
    public Files.Delete delete(SharedFileId fileId)
    {
        GetApiRequest apiRequest = api.get(DELETE, builder ->
                builder.put(FILE, fileId.id()));
        
        return apiRequest.issue(EmptyResult::new);
    }

    @ApiIssuer
    @Override
    public Files.Info info(SharedFileId fileId)
    {
        GetApiRequest apiRequest = api.get(INFO, builder ->
            builder.put(FILE, fileId.id()));

        return apiRequest.issue(FilesImpl.Info::new);
    }

    @ApiIssuer
    @Override
    public Files.List list()
    {
        GetApiRequest apiRequest = api.get(LIST);

        return apiRequest.issue(FilesImpl.List::new);
    }

    private final class Info extends ApiResult implements Files.Info
    {
        private SharedFile file;
        private java.util.List<SharedFileComment> comments;
        private Paging paging;

        @Override
        public SharedFile file()
        {
            return file;
        }

        @Override
        public java.util.List<SharedFileComment> comments()
        {
            return comments;
        }

        @Override
        public Paging paging()
        {
            return paging;
        }

        @Override
        protected void apply(JsonObject result)
        {
            this.file = slack.getConfigure().unmarshaller().asSharedFile(result.getJsonObject(FILE));
            this.comments = slack.getConfigure().unmarshaller().asSharedFileComments(result.getJsonArray(COMMENTS));
            this.paging = PagingImpl.unmarshal(result.getJsonObject(PAGING));
        }
    }

    private final class List extends ApiResult implements Files.List
    {
        private java.util.List<SharedFile> files;
        private Paging paging;

        @Override
        public java.util.List<SharedFile> files()
        {
            return files;
        }

        @Override
        public Paging paging()
        {
            return paging;
        }

        @Override
        protected void apply(JsonObject result)
        {
            this.files = slack.getConfigure().unmarshaller().asSharedFiles(result.getJsonArray(FILES));
            this.paging = PagingImpl.unmarshal(result.getJsonObject(PAGING));
        }
    }
}
