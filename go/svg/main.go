package main

import (
	"fmt"
	"os"

	s "github.com/ajstarks/svgo"
)

func main() {
	// http.Handle("/badge", http.HandlerFunc(circle))
	// err := http.ListenAndServe(":2003", nil)
	// if err != nil {
	// 	log.Fatal("ListenAndServe:", err)
	// }
	badge()
}

// func circle(w http.ResponseWriter, req *http.Request) {

func badge() {
	// w.Header().Set("Content-Type", "image/svg+xml")
	f, err := os.Create("version.svg")
	if err != nil {
		fmt.Println("não foi possivel criar o arquivo version.svg:", err)
		os.Exit(1)
	}
	defer f.Close()
	rainbow := []s.Offcolor{
		{Offset: 57, Color: "#565656", Opacity: 1},
		{Offset: 43, Color: "#136493", Opacity: 1}}
	width := 80
	height := 18
	canvas := s.New(f)
	canvas.Start(width, height)
	canvas.LinearGradient("rainbow", 0, 0, 100, 0, rainbow)
	canvas.Roundrect(0, 0, width, height, 5, 5, "fill:url(#rainbow)")

	canvas.Gstyle("text-anchor:middle;font-family:sans;fill:white")
	canvas.Textspan(width/2, 12, "version ", "font-size:10px")
	canvas.Span("0.2.6", "font-family:serif;font-size:10px;fill:white")
	canvas.TextEnd()
	canvas.Gend()
	canvas.End()
	// canvas.Roundrect(width, height, 200, 200, 10, 10, "teste")
}
