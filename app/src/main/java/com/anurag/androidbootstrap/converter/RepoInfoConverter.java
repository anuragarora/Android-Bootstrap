package com.anurag.androidbootstrap.converter;

import com.anurag.androidbootstrap.model.Repo;
import com.anurag.androidbootstrap.model.ServerRepo;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Anurag_Arora on 3/2/2016.
 */
public class RepoInfoConverter {
    private final Converter.Factory mOriginalConverter;

    public RepoInfoConverter(Converter.Factory originalConverter) {
        mOriginalConverter = originalConverter;
    }

    @Override
    public Converter<ResponseBody, ?> fromResponseBody(final Type type, final Annotation[] annotations) {
        if (ServerRepo.class != type) {
            return mOriginalConverter.responseBodyConverter(type, annotations, null);
        } else {
            return new Converter<ResponseBody, Repo>() {
                @Override
                public Repo convert(ResponseBody value) throws IOException {
                    ServerRepo response = new Gson()
                            .fromJson(value.charStream(), ServerRepo.class);
                    return new Repo();
                }
            };
        }
    }

    @Override
    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        return mOriginalConverter.toRequestBody(type, annotations);
    }
}
