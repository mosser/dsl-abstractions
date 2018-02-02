#include <avr/io.h>
#include <util/delay.h>
#include <Arduino.h>

int led = 10;

void state_off();
void state_on();

void state_on() {
  digitalWrite(led, HIGH);
  _delay_ms(1000);
  state_off();
}

void state_off() {
  digitalWrite(led, LOW);
  _delay_ms(1000);
  state_on();
}

int main(void) {
  pinMode(led, OUTPUT);
  state_on();
  return 0;
}
