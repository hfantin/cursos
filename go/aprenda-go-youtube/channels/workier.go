package main

import (
	"fmt"
	"sync"
)

type workerChannel chan uint64

const num_workers = 5

func main() {

	results := make(chan string)
	workCh := make(workerChannel)

	// Start workers
	var wg sync.WaitGroup
	wg.Add(num_workers)
	for i := 0; i < num_workers; i++ {
		go func(num int) {
			defer wg.Done()
			// Loop processing work until workCh is closed
			for w := range workCh {
				results <- fmt.Sprintf("worker %d, task %d", num, w)
			}

		}(i)
	}

	// Close result channel when workers are done
	go func() {
		wg.Wait()
		close(results)
	}()

	// Send work to be done
	go func() {
		for i := 0; i < 21; i++ {
			workCh <- uint64(i)
		}
		// Closing the channel causes workers to break out of loop
		close(workCh)
	}()

	// Process results. Loop exits when result channel is closed.
	for r := range results {
		fmt.Println(r)
	}
}
