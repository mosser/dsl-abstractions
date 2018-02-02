#include <avr/io.h>
#include <util/delay.h>
#include <Arduino.h>

int led = 10;

bool constant_producer_MSG;
void constant_producer() {
  constant_producer_MSG = true;
}

bool blinker_consumer_state = true;
void blinker_consumer() {
  // bool received_message = constant_producer_MSG;
  if(blinker_consumer_state)  digitalWrite(led, HIGH);
  if(!blinker_consumer_state) digitalWrite(led, LOW);
  blinker_consumer_state = !blinker_consumer_state;
}

int main(void) {
  pinMode(led, OUTPUT);
  while(1) {
    constant_producer();
    blinker_consumer();
    _delay_ms(1000);
  }
  return 0;
}
