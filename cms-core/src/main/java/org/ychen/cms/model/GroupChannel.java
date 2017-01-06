package org.ychen.cms.model;

import javax.persistence.*;

/**
 * Created by cy on 16/12/26.
 */
@Entity
@Table(name="t_group_channel")
public class GroupChannel {
    private int id;
    private Group group;
    private Channel channel;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name="g_id")
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }

    @ManyToOne
    @JoinColumn(name="c_id")
    public Channel getChannel() {
        return channel;
    }
    public void setChannel(Channel channel) {
        this.channel = channel;
    }


}
