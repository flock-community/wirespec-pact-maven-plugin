package community.flock.wirespec.plugins.pact

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class GenerateProducerPactContractsMojoTest {
    @Test
    fun `Consumer contract for wirespec-io should always be the same for the same seed`() {
        val (name, content) = getProducerPact("example-a-consumer-of-my-api--playground.json")
        name shouldBe "example-a-consumer-of-my-api--playground"

        //language=json
        content shouldBe
            """
            {
                "consumer": {
                    "name": "a-consumer-of-my-api"
                },
                "producer": {
                    "name": "example"
                },
                "interactions": [
                    {
                        "description": "GET todos/{id}",
                        "request": {
                            "method": "GET",
                            "path": "todos/{id}",
                            "query": "",
                            "headers": {}
                        },
                        "response": [
                            {
                                "id": "50ab5E2E-d056-aE30-c4e3-9EE1735FDF45",
                                "name": "F0bbyu06zhF2HiEY",
                                "done": false
                            },
                            {
                                "id": "c2a31343-5a7B-F4c6-ddB5-79ce4beBaCB4",
                                "name": "2gGxnpCEGQAm46LfqiyAANT6kc7WvdH_Dq9p",
                                "done": false
                            },
                            {
                                "id": "1BFEad8c-aAef-e3A2-3AAC-8BFF569F1A01",
                                "name": "42iGJBOlkZys4JZTgzNu2JHs",
                                "done": true
                            },
                            {
                                "id": "4ec3122f-43B9-1aC5-8dD4-Faab5EAf32E5",
                                "name": "APrWBgacHbEBC0RCZLSi2bHy",
                                "done": false
                            },
                            {
                                "id": "feca7eA3-3Cb4-4A0A-3AD5-bA8d797ffdAE",
                                "name": "50mdkAEvBQ1zIHZjVLBd9jeXzmcpfEGYEHMG",
                                "done": true
                            },
                            {
                                "id": "A4b9553b-2F3e-ddA0-C8Bb-B2c1D2564E24",
                                "name": "k3juFs8M73FhevAVi7PF5K6f",
                                "done": true
                            },
                            {
                                "id": "FdFBef8b-Dd43-9AAF-BBd4-13F2aaF4B243",
                                "name": "lbleI3XpRA2fIoZCsInCH0uS7gpHcLygwCJYu",
                                "done": true
                            },
                            {
                                "id": "DA34aEc5-D270-4c1D-a3c1-C1fBAbFf7fFB",
                                "name": "wBZ1lIpzZO1iPYkAnr703K",
                                "done": false
                            },
                            {
                                "id": "E98d53B2-dea0-B033-AeA7-055aceAD2DD5",
                                "name": "nVGQqrPGkyGnMhf6kn7RGlqwfo3vJ",
                                "done": true
                            }
                        ]
                    },
                    {
                        "description": "GET todos",
                        "request": {
                            "method": "GET",
                            "path": "todos",
                            "query": "done=false",
                            "headers": {
                                "limit": 1661920713,
                                "offset": -679363925
                            }
                        },
                        "response": [
                            {
                                "id": "9d0dA79A-B32F-BC9C-d47F-c2E194F15A36",
                                "name": "Ge",
                                "done": true
                            },
                            {
                                "id": "D5DDFbc3-3ea3-20cF-Ed03-F9a9aFee1aE3",
                                "name": "v_pV_4g2DEoyYoxLVPLng1s",
                                "done": true
                            }
                        ]
                    },
                    {
                        "description": "POST todos",
                        "request": {
                            "method": "POST",
                            "path": "todos",
                            "query": "",
                            "headers": {}
                        },
                        "response": {
                            "id": "FbC21985-8C0a-5bEF-369c-2EbbCD92abb8",
                            "name": "Eku1WsQ5JZVVL_8vtq_I4LIRHBMwPQojB0ml4J3hNBb8uTM",
                            "done": false
                        }
                    },
                    {
                        "description": "PUT todos/{id}",
                        "request": {
                            "method": "PUT",
                            "path": "todos/{id}",
                            "query": "",
                            "headers": {}
                        },
                        "response": {
                            "id": "4787bdda-82B3-de8D-C9a2-6d45ACb36046",
                            "name": "eCMpwxlWHyK0ro00s52CZWXkvqB1x5YJSSX8Yqep",
                            "done": false
                        }
                    },
                    {
                        "description": "DELETE todos/{id}",
                        "request": {
                            "method": "DELETE",
                            "path": "todos/{id}",
                            "query": "",
                            "headers": {}
                        },
                        "response": {
                            "id": "a9a0afAA-e32f-b5A3-00aB-76207b9375F2",
                            "name": "NCHvo1DKTf5QnIRK09OB6B5tigx7BfOHAJp",
                            "done": false
                        }
                    }
                ],
                "messages": [
                    {
                        "description": "Todo",
                        "contents": {
                            "id": "EF6e134A-b7A0-0d89-A693-DecfFc67dBD8",
                            "name": "yIKJDaC72BI8M1Sweap5dUcKkDalBWrUcI_KfrLi",
                            "done": false
                        }
                    }
                ],
                "metadata": {
                    "pactSpecification": {
                        "version": "3.0.0"
                    },
                    "pactJvm": {
                        "version": "3.3.3"
                    }
                }
            }
            """.trimIndent()
    }

    private fun getProducerPact(input: String): FileContent = getFileContents("./target/pacts/producer/$input")
}
