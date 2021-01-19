dev:
	mvn clean
	mvn install -DskipTests=true
	docker build -f Dockerfile -t lessonservice .
	docker-compose up
