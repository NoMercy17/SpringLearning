package org.proxies;

import org.model.Comment;

public interface CommentNotificationProxy {

    void sendComment(Comment comment);
}
