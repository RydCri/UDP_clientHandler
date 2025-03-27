This repo is to preserve a simple demonstration of a UDP exchange.


<br></br>
<h4> Running <span style="color:green">Game.main()</span> generates a dialogue box. </h4>

<img src="src/main/resources/Jframe.png" style="height: 250px;" alt="">
<p>Clicking Yes creates 2 sockets on localhost, a <span style="color:orange">server</span> on port 1501 and a <span style="color:yellow">client</span> dynamically assigned a port.</p>

The <span style="color:yellow">client</span> sends a packet with the message 'ping' to the <span style="color:orange">server</span>. </br>
The <span style="color:orange">server</span>, if it receives the message, replies to the <span style="color:yellow">client</span> 'pong'.
<br></br>
<img src="src/main/resources/ping_pong.png" style="height: 150px;" alt="">