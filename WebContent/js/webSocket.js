let webSocket = new WebSocket("ws://172.20.10.6/webSocketControl");

function createMessage(command, room, message){
	return '{"command" : "' + command + '", "room" : "' + room + '", "message" : "' + message + '"}'
}