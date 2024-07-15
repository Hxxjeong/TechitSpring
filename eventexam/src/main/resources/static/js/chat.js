const ws = new WebSocket("ws://" + window.location.host + "/ws");

ws.onmessage = (event) => {
    const chatMessage = document.getElementById("chatMessages");
    const chatElement = document.createElement("p");

    chatElement.innerText = event.data;
    chatMessage.appendChild(chatElement);
    chatMessage.scrollTop = chatMessage.scrollHeight;   // 자동 스크롤
};

const sendMessage = () => {
    const message = document.getElementById("message").value;
    if(message.trim() !== "") {
        ws.send(message);
        document.getElementById("message").value = '';
        document.getElementById("message").focus();
    }
};

// enter가 입력되었을 때도 메시지 전송
document.getElementById("message").addEventListener("keypress", (event) => {
    if(event.key === "Enter") {
        sendMessage();
        event.preventDefault();
    }
});

window.onload = () => {
    document.getElementById("message").focus();
}