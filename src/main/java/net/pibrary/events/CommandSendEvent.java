package net.pibrary.events;

import discord4j.common.util.Snowflake;

import java.util.List;

public class CommandSendEvent {
    private List<String> args;
    private Snowflake channelId;

    public CommandSendEvent(List<String> args, Snowflake channelId) {
        this.args = args;
        this.channelId = channelId;
    }

    public int getArgSize() {
        return args.size();
    }

    public List<String> getArgs() {
        return args;
    }

    public String getArg(int index) {
        return args.get(index);
    }

    public Snowflake getChannelId() {
        return channelId;
    }
}
