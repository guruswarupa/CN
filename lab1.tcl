set ns [new Simulator]

set nf [open lab1.nam w]
$ns namtrace-all $nf

set tf [open lab1.tr w]
$ns trace-all $tf

proc finish { } {
    global ns nf tf
    $ns flush-trace
    close $nf
    close $tf
    exec nam lab1.nam &
    exit 0
}

# Create nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]

# Create links
$ns duplex-link $n0 $n2 25Mb 10ms DropTail
$ns duplex-link $n1 $n2 15Mb 5ms DropTail
$ns queue-limit $n0 $n2 10
$ns queue-limit $n1 $n2 10

# Agent 1: UDP on n0 -> n3
set udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0

set cbr0 [new Application/Traffic/CBR]
$cbr0 set packetSize_ 500
$cbr0 set interval_ 0.005
$cbr0 attach-agent $udp0

# Agent 2: UDP on n1 -> n3
set udp1 [new Agent/UDP]
$ns attach-agent $n1 $udp1

set cbr1 [new Application/Traffic/CBR]
$cbr1 set packetSize_ 500
$cbr1 set interval_ 0.010
$cbr1 attach-agent $udp1

# Null Agent on destination (n3)
set null0 [new Agent/Null]
$ns attach-agent $n3 $null0

# Connect both senders to null agent
$ns connect $udp0 $null0
$ns connect $udp1 $null0

# Start traffic
$ns at 0.1 "$cbr0 start"
$ns at 0.2 "$cbr1 start"

# End simulation
$ns at 1.0 "finish"

# Run the simulation
$ns run

