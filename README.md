Feel free to download and analyze the project. Developed in Intellij, Maven as build tool.

API uses github API Authorization Key, if You wish to run the application, You should insert your key into application.yml properties file.

Below are presented screenshots of Postman with the API calls:
1. Getting repos from existing user
   ![scr1](https://github.com/user-attachments/assets/95c4994a-638c-4eaf-89e5-ae8d82753429)
2. Getting repos from nonexisting user
   ![scr2](https://github.com/user-attachments/assets/066b0845-2beb-4dc1-a40c-8ac2a0908389)
3. Getting repos from user, but his repos has more branches than 1
   ![scr4](https://github.com/user-attachments/assets/51c9eb37-6d67-467b-be7c-76e302a17d9b)
4. Sending request without Accept: application/json header included
   ![scr3](https://github.com/user-attachments/assets/0539c13b-0983-4fd1-846e-231aa5192731)
5. Getting repos from user with no public repos
   ![scr5](https://github.com/user-attachments/assets/208c4710-60d8-4107-b90e-530f6a0b45b2)
