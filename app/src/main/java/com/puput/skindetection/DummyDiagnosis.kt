package com.puput.skindetection

import com.puput.skindetection.model.DiagnosisModel

object DummyDiagnosis {
    fun generateDiagnosis(): List<DiagnosisModel> {
        val data = ArrayList<DiagnosisModel>()

        data.add(
                DiagnosisModel(

                    "actinic keratosis : An actinic keratosis (ak-TIN-ik ker-uh-TOE-sis) is a rough, scaly patch on the skin that develops from years of sun exposure. It's often found on the face, lips, ears, forearms, scalp, neck or back of the hands. Also known as a solar keratosis, an actinic keratosis grows slowly and usually first appears in people over 40. You can reduce your risk of this skin condition by minimizing your sun exposure and protecting your skin from ultraviolet (UV) rays. Left untreated, the risk of actinic keratoses turning into a type of skin cancer called squamous cell carcinoma is about 5% to 10%."
                )

        )
        data.add(
                DiagnosisModel(

                        "Basal cell carcinoma is a type of skin cancer. Basal cell carcinoma begins in the basal cells — a type of cell within the skin that produces new skin cells as old ones die off. Basal cell carcinoma often appears as a slightly transparent bump on the skin, though it can take other forms. Basal cell carcinoma occurs most often on areas of the skin that are exposed to the sun, such as your head and neck. Most basal cell carcinomas are thought to be caused by long-term exposure to ultraviolet (UV) radiation from sunlight. Avoiding the sun and using sunscreen may help protect against basal cell carcinoma."
                )

        )
        data.add(
                DiagnosisModel(

                        "Seborrheic keratoses are noncancerous (benign) skin growths that some people develop as they age. They often appear on the back or chest, but can occur on any part of the body. Seborrheic keratoses grow slowly, in groups or singly. Most people will develop at least one seborrheic keratosis during their lifetime."
                )

        )
        data.add(
                DiagnosisModel(

                        "Dermatofibromas are harmless growths within the skin that usually have a small diameter. They can vary in color, and the color may change over the years.Dermatofibromas are firm to the touch. They are very dense, and many people say they feel like a small stone underneath or raised above the skin. Most dermatofibromas are painless. Some people experience itching or irritation at the site of the growth, as well as tenderness. Dermatofibromas may also be called benign fibrous histiocytomas."
                )

        )
        data.add(
                DiagnosisModel(

                        "Melanoma is a type of skin cancer that occurs when pigment producing cells called melanocytes mutate and begin to divide uncontrollably. Most pigment cells develop in the skin. Melanomas can develop anywhere on the skin, but certain areas are more at risk than others. In men, it is most likely to affect the chest and back. In women, the legs are the most common site. Other common sites of melanoma include the face.However, melanoma can also occur in the eyes and other parts of the body, including — on very rare occasions — the intestines.Melanoma is relatively rare in people with darker skin.The American Cancer Society (ACS) estimate that there will be about 96,480 new diagnoses of melanoma in 2019. They also estimate that around 7,230 people will die due to melanoma in 2019."  )

        )
        data.add(
                DiagnosisModel(

                        "A melanocytic naevus (American spelling ‘nevus’), or mole, is a common benign skin lesion due to a local proliferation of pigment cells (melanocytes). It is sometimes called a naevocytic naevus or just 'naevus' (but note that there are other types of naevi). A brown or black melanocytic naevus contains the pigment melanin, so may also be called a pigmented naevus"
                )

        )
        data.add(
                DiagnosisModel(

                        "angiomas : Red moles, or cherry angiomas, are common skin growths that can develop on most areas of your body. They’re also known as senile angiomas or Campbell de Morgan spots.They’re usually found on people aged 30 and older. The collection of small blood vessels inside a cherry angioma give them a reddish appearance.This type of skin growth is typically not a cause for concern unless it bleeds often or changes in size, shape, or color. Talk to your doctor if you notice any bleeding or changes in appearance. These could be symptoms of skin cancer."
                )

        )
        return data
    }
}