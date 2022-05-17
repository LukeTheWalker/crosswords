#include <stdio.h>

static char *super[] = {"\xe2\x81\xb0", "\xc2\xb9", "\xc2\xb2",
    "\xc2\xb3", "\xe2\x81\xb4", "\xe2\x81\xb5", "\xe2\x81\xb6",
    "\xe2\x81\xb7", "\xe2\x81\xb8", "\xe2\x81\xb9"};
static char *sub[] = {"\xe2\x82\x80", "\xe2\x82\x81", "\xe2\x82\x82",
    "\xe2\x82\x83", "\xe2\x82\x84", "\xe2\x82\x85", "\xe2\x82\x86",
    "\xe2\x82\x87", "\xe2\x82\x88", "\xe2\x82\x89"};

int main(void) {
    int i;
    printf ("f(x) = x%s + log%sx\n",super[2],sub[2]);
    for (i = 0; i < 10; i++) {
        printf ("x%s x%s ", super[i], sub[i]);
    }
    printf ("y%s%s%s z%s%s\n", super[9], super[9], super[9], sub[7], sub[5]);
    return 0;
}
