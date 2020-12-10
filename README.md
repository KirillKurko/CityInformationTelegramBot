# City Information Telegram Bot

This repository contains a telegram bot that uses a REST API to get information about cities. 

You can find bot source code in a `bot` package and REST API source code in a `service` package.

## City Information REST API

You can use next endpoints:

* `GET http://localhost:8080/cityInformation/cities` - Get all cities.
* `GET http://localhost:8080/cityInformation/cities/{city}` - Get city with `{city}` name.
* `POST http://localhost:8080/cityInformation/cities` - Add a new city.
* `PUT http://localhost:8080/cityInformation/cities` - To update an existing city information.
* `DELETE http://localhost:8080/cityInformation/cities{city}` - To delete city information with `{city}` name.

## Database 

REST API uses a MySQL database to store information about cities. You can use `CityInforamtion.sql` to generate database schema.

## Telegram Bot

To run the bot you need to know its name and token. You can use my bot name and token or you can create you own bot.
* name: `PenguinTravelerBot`
* token: `1466555555:AAGKVSaV4maTFQ-Z-PiPBD6tt8MTGtOH3qg`

## Configuration

If you want to use your own bot then you need to change the name and token in the `Application` class in the `bot` package.
```
public class Application {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        //Place your bot name and token in line below
        Bot bot = new Bot("PenguinTravelerBot", "1466555555:AAGKVSaV4maTFQ-Z-PiPBD6tt8MTGtOH3qg");
        bot.connect();
    }
}
```

Also you may need to change `application.properties` file.
You need to specify own `database URL`, `username` and `password`.
```
spring.datasource.url=jdbc:mysql://localhost:3306/CityInformation?charset=UTF8
spring.datasource.username=root
spring.datasource.password=12qazwsx
```

## Configuration

When the configuration is done you just need to run `CityInformationApplication` to make our REST API work and `Aplication` to start the bot. 