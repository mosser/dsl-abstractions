#include <avr/io.h>
#include <util/delay.h>
#include <Arduino.h>

int led = 10;

int main(void) {

  pinMode(led, OUTPUT);

  while(1) {
    digitalWrite(led, HIGH);
    _delay_ms(1000);
    digitalWrite(led, LOW);
    _delay_ms(1000);
  }

  return 0;

}
