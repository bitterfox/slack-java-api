/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.event;

/**
 *
 * @author bitter_fox
 */
public interface Message extends Event
{
    enum Subtype
    {
        NONE,
        ;
        // TODO
    }

    String text();
//    List<TextElement> textElements();
    String timestamp(); // TODO

    Subtype subtype();

    void visit(MessageVisitor visitor);
}
