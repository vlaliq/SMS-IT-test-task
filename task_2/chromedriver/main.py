from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
import time

url = "https://www.wildberries.ru/"
driver_path = "Вставьте_путь_до_chromedriver.exe"

service = Service(driver_path)
driver = webdriver.Chrome(service=service)

try:
    driver.get(url=url)
    time.sleep(3)

    input = driver.find_element(By.ID, "searchInput")
    input.clear()
    input.send_keys("транспортир")
    input.send_keys(Keys.ENTER)  # Нажатие клавиши Enter
    time.sleep(2)

    sorting = driver.find_element(By.XPATH, "//button[text()='По популярности']")
    sorting.click()
    sorting_ascending = driver.find_element(By.XPATH, "//span[text()='По возрастанию цены']")
    sorting_ascending.click()
    time.sleep(2)

    # Находим все элементы с товарами на странице
    items = driver.find_elements(By.CLASS_NAME, "product-card__wrapper")
    time.sleep(5)

    # Выводим первые 10 элементов (название и цена)
    for i in range(10):
        item = items[i]
        name = item.find_element(By.CLASS_NAME, "product-card__brand-wrap")
        price = item.find_element(By.XPATH, ".//ins[@class='price__lower-price wallet-price']")
        print(f"Товар {i + 1}: {name.text} - Цена: {price.text.strip().split()[0]} руб")

except Exception as ex:
    print(ex)
finally:
    driver.close()
    driver.quit()


