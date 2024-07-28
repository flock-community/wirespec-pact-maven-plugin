package community.flock.wirespec.plugins.pact

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class GenerateConsumerPactContractsMojoTest {
    @Test
    fun `Consumer contract for wirespec-io should always be the same for the same seed`() {
        val (name, content) = getConsumerPact("wirespec.io-example--playground.json")
        name shouldBe "wirespec.io-example--playground"

        //language=json
        content shouldBe
            """
            {
                "consumer": {
                    "name": "example"
                },
                "producer": {
                    "name": "wirespec.io"
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
                                "id": "1fCC5eDC-1daE-4b35-3398-FbCDA451ade5",
                                "name": "B16tH_p1f6cWFvZwaqB5_dODETUBUdwRGXYh70t7UQU",
                                "done": false
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
                                "limit": 934827088,
                                "offset": 1029833079
                            }
                        },
                        "response": [
                            {
                                "id": "FBFe25c3-EB20-4945-bf0E-4e8de8fcEa0f",
                                "name": "7dAYy80cbPB1xVslCCSXvpT17Db4APBcIE5W",
                                "done": true
                            },
                            {
                                "id": "7215dF60-F1Ce-3A17-4dA0-d8a9fCdBD6Be",
                                "name": "p0bzk",
                                "done": false
                            },
                            {
                                "id": "538c5dEc-Edf3-e6Ea-Bafb-9Aa561E613ba",
                                "name": "27tBAyeetQ9QqScC3gmkljDz015RwpG",
                                "done": false
                            },
                            {
                                "id": "69Fbebbc-A9bF-dDEf-82A8-b23CbA9cECD2",
                                "name": "tT1BLH7WtG_Tk8Sn4SeTpIf3X6HopuPcsuVvfal",
                                "done": false
                            },
                            {
                                "id": "4C5Cafb6-0E07-F6b0-6A05-0DC08Fc08A98",
                                "name": "qbsZZf0JpT1zwS1biziBnTgpvvHgqJh04gcftwtBWvZroCa",
                                "done": true
                            },
                            {
                                "id": "7C5d7c8D-fa9C-15b9-5B3B-cfFA6ad3c941",
                                "name": "rcjNlD527OjAdOi7h1VGPDjdB62oqs",
                                "done": true
                            },
                            {
                                "id": "dd1521ED-e506-384d-3D58-0eCAC2DfeCe4",
                                "name": "8kQZUamRUhfX5N34rFl1NsO3Po2XI4E",
                                "done": true
                            },
                            {
                                "id": "74dbb6D8-bb25-581F-1658-AF751507f2aE",
                                "name": "Xiamv9xORdL8V9l2M6stam5N3fx8hhp62BoW0xNFnN",
                                "done": false
                            },
                            {
                                "id": "19ee8d6C-FbaA-44E2-E5aD-49f40fF5fbCA",
                                "name": "RUn6dK_na2jr_3",
                                "done": true
                            },
                            {
                                "id": "EEB02999-CeF9-B45F-eac8-DAD9147cfc7f",
                                "name": "rwDIKkCar1UtnQjkS7XUGntEgQ76gJyeNqSk5zl64Mj",
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
                            "id": "7A66eF0b-7BD8-7Cab-4ABa-55adDABEBEf0",
                            "name": "Mo_",
                            "done": true
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
                            "id": "b4042BCE-5B24-01DA-Bee0-F9D49De2fB70",
                            "name": "PTKLgHu88L7",
                            "done": true
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
                            "id": "11664d03-2Bba-3AE5-BD8d-7bC7A0dC10C6",
                            "name": "QIJggPNYU0Fv1LGcRwfzpbO2",
                            "done": true
                        }
                    }
                ],
                "messages": [
                    {
                        "description": "Todo",
                        "contents": {
                            "id": "eFD47DB7-30ED-d5d0-eafe-8c3a85eE4822",
                            "name": "peTPfGv3KyUXQBfdA6TmQOmnH4AEHW",
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

    private fun getConsumerPact(input: String): FileContent = getFileContents("./target/pacts/consumer/$input")
}
