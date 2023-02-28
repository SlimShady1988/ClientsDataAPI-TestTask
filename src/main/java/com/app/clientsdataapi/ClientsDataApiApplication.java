package com.app.clientsdataapi;

import com.app.clientsdataapi.entity.Client;
import com.app.clientsdataapi.entity.Gender;
import com.app.clientsdataapi.entity.Occupation;
import com.app.clientsdataapi.repository.ClientRepository;
import com.app.clientsdataapi.repository.OccupationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ClientsDataApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientsDataApiApplication.class, args);
    }


	/**
	 * Цей бін лише для мокових даних та для перевірки пагінації.
	 * Було б добре його закоментувати після створення цих даних.
	 * Також я додав обмеження на Тайтли професій, щоб вони не повторювались,
	 * через що буде виникати помилка при повторній спробі створення дублікатів.
	*/
	@Bean
	CommandLineRunner runner(ClientRepository clientRepository, OccupationRepository occupationRepository) {


		return args -> {
			if (!occupationRepository.existsByName("Розробник")) {
				Occupation occupation = new Occupation("Розробник", Gender.MALE);
				Occupation occupation1 = new Occupation("Будівельник", Gender.MALE);
				Occupation occupation2 = new Occupation("Гончар", Gender.MALE);
				Occupation occupation3 = new Occupation("Військовий", Gender.MALE);
				Occupation occupation4 = new Occupation("Міністр", Gender.MALE);
				Occupation occupation5 = new Occupation("UI Дизайнер", Gender.FEMALE);
				Occupation occupation6 = new Occupation("UX Дизайнер", Gender.FEMALE);
				Occupation occupation7 = new Occupation("Кухарка", Gender.FEMALE);
				Occupation occupation8 = new Occupation("Прибиральниця", Gender.FEMALE);
				Occupation occupation9 = new Occupation("Стюардеса", Gender.FEMALE);

				occupationRepository.insert(occupation);
				occupationRepository.insert(occupation1);
				occupationRepository.insert(occupation2);
				occupationRepository.insert(occupation3);
				occupationRepository.insert(occupation4);
				occupationRepository.insert(occupation5);
				occupationRepository.insert(occupation6);
				occupationRepository.insert(occupation7);
				occupationRepository.insert(occupation8);
				occupationRepository.insert(occupation9);

				Client client = new Client(
						"Степан",
						"Борсук",
						"Ігорович",
						1962,
						Gender.MALE,
						"Гончар"
				);

				Client client0 = new Client(
						"Віктор",
						"Клим",
						"Олегович",
						1995,
						Gender.MALE,
						"Розробник"
				);
				Client client1 = new Client(
						"Марина",
						"Кругляк",
						"Вікторівна",
						1993,
						Gender.FEMALE,
						"UX Дизайнер"
				);
				Client client2 = new Client(
						"Жанна",
						"Петренко",
						"Іванівна",
						1998,
						Gender.FEMALE,
						"Стюардеса"
				);
				Client client3 = new Client(
						"Кейт",
						"Мідлтон",
						"Вікторівна",
						1978,
						Gender.FEMALE,
						"UI Дизайнер"
				);
				Client client4 = new Client(
						"Жан",
						"Кусто",
						"Жакович",
						1948,
						Gender.MALE,
						"Міністр"
				);
				clientRepository.insert(client0);
				clientRepository.insert(client1);
				clientRepository.insert(client2);
				clientRepository.insert(client);
				clientRepository.insert(client3);
				clientRepository.insert(client4);
			}

		};
	}
}
