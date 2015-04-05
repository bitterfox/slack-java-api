/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl.event;

import com.slack.data.event.Event;
import com.slack.data.event.Message;
import com.slack.data.event.MessageVisitor;
import java.util.Objects;

/**
 *
 * @author bitter_fox
 */
public class MessageImpl implements Message
{
    private String text;
    private String timestamp;

    public void text(String text)
    {
        this.text = text;
    }

    @Override
    public String text()
    {
        return text;
    }

    public void timestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    @Override
    public String timestamp()
    {
        return timestamp;
    }

    @Override
    public Subtype subtype()
    {
        return Message.Subtype.NONE;
    }

    @Override
    public void visit(MessageVisitor visitor)
    {
        visitor.acceptMessage(this);
    }

    @Override
    public Type type()
    {
        return Event.Type.MESSAGE;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.text);
        hash = 89 * hash + Objects.hashCode(this.timestamp);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final MessageImpl other = (MessageImpl)obj;
        if (!Objects.equals(this.text, other.text))
        {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "MessageImpl{" + "text=" + text + ", timestamp=" + timestamp + '}';
    }
}
