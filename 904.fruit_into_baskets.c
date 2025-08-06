#include <stdio.h>

// Your optimized totalFruit function
int totalFruit(int* fruits, int fruitsSize) {
    int lastFruit = -1, secondLastFruit = -1;
    int lastFruitCount = 0;
    int currentMax = 0, maxFruits = 0;

    for (int i = 0; i < fruitsSize; i++) {
        int fruit = fruits[i];

        if (fruit == lastFruit || fruit == secondLastFruit) {
            currentMax++;
        } else {
            currentMax = lastFruitCount + 1;
        }

        if (fruit == lastFruit) {
            lastFruitCount++;
        } else {
            lastFruitCount = 1;
            secondLastFruit = lastFruit;
            lastFruit = fruit;
        }

        if (currentMax > maxFruits)
            maxFruits = currentMax;
    }

    return maxFruits;
}

// Main function to test totalFruit
int main() {
    int fruits[] = {1, 2, 1, 2, 3};
    int size = sizeof(fruits) / sizeof(fruits[0]);

    int result = totalFruit(fruits, size);
    printf("Maximum fruits collected: %d\n", result);

    return 0;
}