package homework3.enums;

public enum ServiceMessage {
    SAVE_EXCEPTION("Не удалось записать данные в файл!"),
    SAVE_SUCCESS("Сохранение прошло успешно!"),
    LOAD_EXCEPTION("Не удалось загрузить данные!"),
    LOAD_SUCCESS("Данные вычитаны!");

    private String description;

    ServiceMessage(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
