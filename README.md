Feel free to download and analyze the project. Developed in Intellij, Maven as build tool.

API uses github API Authorization Key, if You wish to run the application, You should insert your key into application.yml properties file.

**Below are presented screenshots of Postman with the API calls:**
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


**The same calls after switching to WebClient from RestClient (noticibly faster responses):**

1.
![1](https://github.com/user-attachments/assets/8791f5db-15d2-4978-941b-ff9dcfcad5c9)
2.
![2](https://github.com/user-attachments/assets/1b3033fa-453e-4509-926e-641fa409142c)
3.
![3](https://github.com/user-attachments/assets/84bf2a52-1686-4e0d-836a-139d0e96bfc4)
4.
![4](https://github.com/user-attachments/assets/b45d2ae1-1d36-48e9-8e18-cd0d989a5ec8)
5.
![5](https://github.com/user-attachments/assets/24c25eb6-268f-4057-8ce9-fbc1e031f847)
