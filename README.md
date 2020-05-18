# tele-task

To start project:
1. Pre-install and start docker on your machine. Project use H2 in-memory database.
2. Go to terminal
3. Clone project: git clone https://github.com/RodionZlobin/tele-task.git
4. Navigate to project root: cd tele-task
5. Build the project: mvn clean install
6. Build the docker image: docker build -t telenor .
7. Run prokect: docker run -p 3000:8080 -t telenor:latest
8. Open your web-browser (or Postman/Swagger) and use urls:
    URL=http://localhost:3000/ - to welcome page
    URL=http://localhost:3000/data - to load data from existing file to H2 DB
    URL=http://localhost:3000/product? (example: http://localhost:3000/product?type=phone&min_price=100&max_price=1000&property=color&property_color=brun)
            GET /product
            Query Parameter			Description
            type					The product type. (String. Can be 'phone' or 'subscription')
            min_price				The minimum price in SEK. (Number)
            max_price				The maximum price in SEK. (Number)
            city					The city in which a store is located. (String)
            property				The name of the property. (String. Can be 'color' or 'gb_limit')
            property:color			The color of the phone. (String)
            property:gb_limit_min 	The minimum GB limit of the subscription. (Number)
            property:gb_limit_max 	The maximum GB limit of the subscription. (Number)
