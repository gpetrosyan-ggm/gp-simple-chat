Simple text base chat based on Netty's framework
======================

## Chat handles the following commands:

- /login \<name\> \<password\>
    - If the user doesn't exist, create profile else login, after login join to last connected
      channel (use join logic, if client’s limit exceeded, keep connected, but without active
      channel).
- /join \<channel\>
    - Try to join a channel (max 10 active clients per channel is needed). If client's limit
      exceeded - send error, otherwise join channel and send last N messages of activity.
- /leave
    - Leave current channel.
- /disconnect
    - Close connection to server.
- /list
    - Send list of channels.
- /users
    - Send list of unique users in current channel.
- \<text message terminated with CR\>
    - Sends message to current channel Server must send a new message to all connected to
      this channel.

## Chat contains the following properties:

#### There are some properties, which can be changed from the 'application.properties' file

```
# Chat server port, can be changed
server.port=8888

# Enables token validations during each command calling
enable.token.validation=false

# Chat channel messages retrieving limit when new user connecting to the channel 
channel.messages.limit.size=10

# Chat channel's users limit
channel.limit.size=10

```

#### There are other properties related to error/success messages

## How to user:

Start the server using an idea, or build the jar file and start it by command

Client Gor

```
 telnet localhost 8888

 /login gor pass

 /join roomA

 hi

```

Client Eve

```
 telnet localhost 8888

 /login eve pass

 /join roomA

 hi

```
