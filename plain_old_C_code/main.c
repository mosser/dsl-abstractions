#include <avr/io.h>
#include <util/delay.h>

int main(void) {
  DDRD |= 0b00000000;   // Configuration register for digital 1 to 7
  DDRB |= 0b00000100;   // Configuration register for digital 8 to 15
                        // Digital 10 in now in outputmode (bit set to 1)

  while(1) {            // infinite loop
      PORTB ^= 0b00000100;
      _delay_ms(1000);  // 1Hz period
  }
  return 0;
}
