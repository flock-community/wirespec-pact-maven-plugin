type UUID /^[0-9a-fA-F]{8}\b-[0-9a-fA-F]{4}\b-[0-9a-fA-F]{4}\b-[0-9a-fA-F]{4}\b-[0-9a-fA-F]{12}$/g

type Todo {
    id: UUID,
    name: String,
    done: Boolean
}

type TodoInput {
    name: String,
    done: Boolean
}

type Error {
    code: String,
    description: String
}

endpoint GetTodoById GET /todos/{ id: UUID } -> {
    200 -> Todo[]
    404 -> Error
}

endpoint GetTodos GET /todos?{ done: Boolean? }#{ limit: Integer, offset: Integer } -> {
    200 -> Todo[]
    404 -> Error
}

endpoint CreateTodo POST TodoInput /todos -> {
    200 -> Todo
    404 -> Error
}

endpoint UpdateTodo PUT TodoInput /todos/{id:UUID} -> {
    200 -> Todo
    404 -> Error
}

endpoint DeleteTodo DELETE /todos/{id:UUID} -> {
    200 -> Todo
    404 -> Error
}

channel TodoChannel -> Todo
