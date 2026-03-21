# WebRTC Videoconferencia Fix - Remote Stream Not Appearing

## Status: In Progress

### Step 1: [COMPLETE] Add Debug Logging and TURN Servers to Frontend
- Edit `src/main/resources/templates/dashboard/CRUDs/Telemedicina.html`
  - Console.log for STOMP connect/subscribe/send/receive
  - Log peerConnection.iceConnectionState changes
  - Add TURN servers (openrelay.metered.ca)
  - Guards in handleSignal (check if peerConnection exists)
  - Role detection? (query param isDoctor?)

### Step 2: Implement Poll Endpoint for Patient Join
- Edit `CitaRoutes.java`: @GetMapping("/sala/{salaId}/offer") return latest offer JSON from DB
- Patient first polls, then creates answer.

### Step 3: Update ServiceTelemedicina to Handle Full JSON
- Modify guardarOffer to accept/parse full offer JSON

### Step 4: Test
- Create virtual cita
- Doctor: /citas/consulta-virtual/{id}
- Patient: /citas/sala/{id}/join (new)
- Check console/network/STOMP frames

### Step 5: [PENDING] Patient UI Integration
- Add join link in exitoCita.html

## Testing Commands
mvn spring-boot:run
Open 2 incognito: localhost:8080/citas/consulta-virtual/1

