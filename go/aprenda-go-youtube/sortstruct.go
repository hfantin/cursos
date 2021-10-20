package main

import (
	"fmt"
	"sort"
)

type user struct {
	First   string
	Last    string
	Age     int
	Sayings []string
}

func main() {
	u1 := user{
		First: "James",
		Last:  "Bond",
		Age:   32,
		Sayings: []string{
			"Shaken, not stirred",
			"Youth is no guarantee of innovation",
			"In his majesty's royal service",
		},
	}

	u2 := user{
		First: "Miss",
		Last:  "Moneypenny",
		Age:   27,
		Sayings: []string{
			"James, it is soo good to see you",
			"Would you like me to take care of that for you, James?",
			"I would really prefer to be a secret agent myself.",
		},
	}

	u3 := user{
		First: "M",
		Last:  "Hmmmm",
		Age:   54,
		Sayings: []string{
			"Oh, James. You didn't.",
			"Dear God, what has James done now?",
			"Can someone please tell me where James Bond is?",
		},
	}

	users := []user{u1, u2, u3}

	// fmt.Println(users)

	// your code goes here
	sort.SliceStable(users, func(i, j int) bool {
		return users[i].Age < users[j].Age //&& users[i].Last < users[j].Last
	})
	for i, v := range users {
		fmt.Printf("%d %s %s %d\n", i, v.First, v.Last, v.Age)
		sort.SliceStable(v.Sayings, func(i, j int) bool {
			return v.Sayings[i] < v.Sayings[j]
		})
		for _, s := range v.Sayings {
			fmt.Println("-", s)
		}
	}

}
