#.PHONY build start stop clean healthcheck

build:
	javac ./src/Main.java

start: build
	java Main $(filename1) $(filename2)

clean:
	rm Main


.PHONY: build start clean
