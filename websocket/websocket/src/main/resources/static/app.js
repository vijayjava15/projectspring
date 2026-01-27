async function fetchRooms() {
    const response = await fetch('/chat/get');
    const rooms = await response.json();
    console.log(rooms)
    const roomList = document.getElementById('roomList');
    roomList.innerHTML = '';
    rooms.forEach(room => {
        roomList.innerHTML += `<li><a href="chatroom.html?room=${room}">${room}</a></li>`;
    });
}

async function createRoom() {
    const roomName = document.getElementById('roomName').value;
    if (roomName.trim()) {
        await fetch('/chat/{room}/addUser', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body:  roomName
        });
        fetchRooms();
    }
}