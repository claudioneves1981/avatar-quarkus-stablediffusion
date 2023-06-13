package domain.models;

public record ProfilePhoto(String id, String originalPhoto, String prompt, String negativePrompt, String width, String height, String samples, String numInferenceSteps, Double guidanceScale, String generatedPhoto) {

    @Override
    public String toString() {
        return "{\n" +
                "\"key\":\"ASJdg4ccuknMEwOXGv9XlE0SJuIhZi96JayHOfnGeh5p5WqWk0tk9hCpldgm\"" +
                ",\n\"prompt\":\"" + prompt + "\"" +
                ",\n\"negative_prompt\":\""+ negativePrompt + "\"" +
                ",\n\"width\":\""+ width + "\"" +
                ",\n\"height\":\""+ height + "\"" +
                ",\n\"samples\":\""+ samples + "\"" +
                ",\n\"num_inference_steps\":\""+ numInferenceSteps + "\"" +
                ",\n\"safety_checker\":\"no\""+
                ",\n\"enhance_prompt\":\"yes\""+
                ",\n\"seed\":"+ null +
                ",\n\"guidance_scale\":" + guidanceScale +
                ",\n\"webhook\":"+ null +
                ",\n\"track_id\":"+ null +
                "\n}";
    }
}
