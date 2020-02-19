package com.myhan.chat_client_practice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.myhan.chat_client_practice.model.ChatMessage
import com.myhan.chat_client_practice.model.ChatRoom
import com.myhan.chat_client_practice.model.User
import io.reactivex.BackpressureStrategy
import io.reactivex.Emitter
import io.reactivex.Flowable

class RepositoryImpl: Repository {
    val sampleChatRooms: List<ChatRoom> = listOf(
        ChatRoom("sampleroomid2", "스프링 스터디"),
        ChatRoom("sampleroomid3", "소식 공유방"),
        ChatRoom("sampleroomid6", "스프링 스터디"),
        ChatRoom("sampleroomid7", "소식 공유방"),
        ChatRoom("sampleroomid10", "스프링 스터디"),
        ChatRoom("sampleroomid11", "소식 공유방"),
        ChatRoom("sampleroomid14", "스프링 스터디"),
        ChatRoom("sampleroomid15", "소식 공유방"),
        ChatRoom("sampleroomid16", "빠른 만남방")
    )

    val sampleLiveChatRooms: MutableLiveData<List<ChatRoom>> = MutableLiveData(sampleChatRooms)
    val sampleFlowableChatRooms: Flowable<List<ChatRoom>> = Flowable.just(listOf(
        ChatRoom("sampleroomid1", "한 만영"),
        ChatRoom("sampleroomid2", "스프링 스터디"),
        ChatRoom("sampleroomid3", "소식 공유방"),
        ChatRoom("sampleroomid4", "황 성호"),
        ChatRoom("sampleroomid5", "한 만영"),
        ChatRoom("sampleroomid6", "스프링 스터디"),
        ChatRoom("sampleroomid7", "소식 공유방"),
        ChatRoom("sampleroomid8", "황 성호"),
        ChatRoom("sampleroomid9", "한 만영"),
        ChatRoom("sampleroomid10", "스프링 스터디"),
        ChatRoom("sampleroomid11", "소식 공유방"),
        ChatRoom("sampleroomid12", "황 성호"),
        ChatRoom("sampleroomid13", "한 만영"),
        ChatRoom("sampleroomid14", "스프링 스터디"),
        ChatRoom("sampleroomid15", "소식 공유방"),
        ChatRoom("sampleroomid16", "황 성호")
    ))

    val sampleLiveUser: MutableLiveData<List<User>> = MutableLiveData(listOf(
        User("sampleuserid4", "재규어", "각하, 정치를 좀 대국적으로 하십쇼"),
        User("sampleuserid5", "박두만", "여기가 강간의 왕국이냐????"),
        User("sampleuserid6", "정마담", "나 이대나온 여자야"),
        User("sampleuserid7", "아귀", "동작그만 밑장빼기냐"),
        User("sampleuserid8", "호구", "예림이 그패봐봐 혹시 장이야?"),
        User("sampleuserid9", "고니", "난 딴돈의 반만 가져가"),
        User("sampleuserid11", "권진수", "으아니 챠! 왜 안 드러 가는 거야!"),
        User("sampleuserid12", "존 월컷", "아 페이퍼타워리 요기잉네?")
    ))
    val sampleFlowableUser: Flowable<List<User>> = Flowable.just(listOf(
        User("sampleuserid4", "재규어", "각하, 정치를 좀 대국적으로 하십쇼"),
        User("sampleuserid5", "박두만", "여기가 강간의 왕국이냐????"),
        User("sampleuserid6", "정마담", "나 이대나온 여자야"),
        User("sampleuserid7", "아귀", "동작그만 밑장빼기냐"),
        User("sampleuserid8", "호구", "예림이 그패봐봐 혹시 장이야?"),
        User("sampleuserid9", "고니", "난 딴돈의 반만 가져가"),
        User("sampleuserid11", "권진수", "으아니 챠! 왜 안 드러 가는 거야!"),
        User("sampleuserid12", "존 월컷", "아 페이퍼타워리 요기잉네?")
    ))

    val sampleMyProfile: Flowable<User> = Flowable.just(
        User("samplemyid", "마틸다", "사는게 항상 이렇게 힘든 건가요? 아니면 어릴 때만 그런 건가요?")
    )

    val sampleMessages = listOf<ChatMessage>(
        ChatMessage(ChatMessage.MessageType.TALK, "sampleroomid", "samplemyid", "하쿠나 마타타"),
        ChatMessage(ChatMessage.MessageType.TALK, "sampleroomid", "sampleotherid", "정말 멋진 말이지"),
        ChatMessage(ChatMessage.MessageType.TALK, "sampleroomid", "samplemyid", "하쿠나 마타타"),
        ChatMessage(ChatMessage.MessageType.TALK, "sampleroomid", "sampleotherid", "끝내주는 말"),
        ChatMessage(ChatMessage.MessageType.TALK, "sampleroomid", "samplemyid", "근심과 걱정 모두 떨처버려")
    )
    val sampleLiveMessages = MutableLiveData<List<ChatMessage>>(sampleMessages)
    
    override fun chatRooms(): LiveData<List<ChatRoom>> {
        return sampleLiveChatRooms
    }

    override fun users(): LiveData<List<User>> {
        return sampleLiveUser
    }

    override fun me(): Flowable<User> = sampleMyProfile
    override fun chatRoom(roomId: String): Flowable<ChatRoom> {
        val sampleFlowableChatRoom: Flowable<ChatRoom> = Flowable.create({
            for (chatroom in sampleChatRooms) {
                if(chatroom.roomId == roomId) {
                    it.onNext(chatroom)
                }
            }
        }, BackpressureStrategy.LATEST)
        return sampleFlowableChatRoom
    }

    override fun messages(roomId: String): LiveData<List<ChatMessage>> = sampleLiveMessages
}