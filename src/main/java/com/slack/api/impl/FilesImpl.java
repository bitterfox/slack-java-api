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
import static com.slack.api.impl.Names.FILES;
import static com.slack.api.impl.Names.LIST;
import static com.slack.api.impl.Names.PAGING;
import com.slack.data.SharedFile;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
public class FilesImpl implements Files
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
    public List list()
    {
        GetApiRequest apiRequest = api.get(LIST);

        return apiRequest.issue(FilesImpl.List::new);
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
