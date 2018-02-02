# PlantUML Configuration
PLANTUML_EXE = ../_third_part_tools/plantuml.jar
DOT = /opt/local/bin/dot
JAVA = java

all: fsm.png reactive.png

%.png: %.puml
	$(JAVA) -jar $(PLANTUML_EXE) -graphvizdot $(DOT) $<

clean:
	rm -rf *.png
