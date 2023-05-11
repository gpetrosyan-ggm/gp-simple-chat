package gp.assessments.chat.utils;

import gp.assessments.chat.common.error.InvalidChannelAttributeNameException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

import java.util.Optional;

/**
 * Utility class for command-based operations, for instance, read/write attributes from/to channel context
 */
public final class CommandUtils {

    private CommandUtils() {
    }

    public static Optional<String> getAttributeByName(ChannelHandlerContext ctx, String attrName) {
        AttributeKey<String> attributeKey = AttributeKey.valueOf(attrName);
        return Optional.ofNullable(ctx.channel().attr(attributeKey).get());
    }

    public static String getAttributeByNameWithException(ChannelHandlerContext ctx, String attrName) {
        return getAttributeByName(ctx, attrName)
                .orElseThrow(() -> new InvalidChannelAttributeNameException(String.format(
                        "Invalid attribute name: %s",
                        attrName)));
    }

    public static void setAttributeByName(ChannelHandlerContext ctx, String attrName, String attrValue) {
        AttributeKey<String> attributeKey = AttributeKey.valueOf(attrName);
        ctx.channel().attr(attributeKey).set(attrValue);
    }

}
